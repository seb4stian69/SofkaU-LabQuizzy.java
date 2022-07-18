package org.sofka.views;

import org.jboss.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.sofka.controller.ObjectQuestion;
import org.sofka.model.ModelQuestion;
import org.sofka.model.ModelQuiz;
import org.sofka.model.ModelUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @LuisaAvila/SebatianSantis
 * @class ViewsQuiz es la clase donde se construe la vista del juego, con el fin de que el jugador visualice la preguntas y de una opcion de respuesta.
 */

public class ViewsQuiz implements InterfaceViews {

    private final Scanner getData = new Scanner(System.in);
    final Logger log = Logger.getLogger("Logger");
    /*
     * @LuisaAvila/SebatianSantis
     * @Object se intancia la clase ModelQuestion para acceder a sus métodos.
     */
    private ModelQuestion question = null;
    private Integer level = 0;
    private Integer score = 0;

    /*
     * @LuisaAvila/SebatianSantis
     * @Object se intancia la clase ViewMenu para acceder a sus métodos.
     */
    private final ViewsMenu menu = new ViewsMenu();
    /*
     * @LuisaAvila/SebatianSantis
     * @Object se intancia la clase ModelUser para acceder a sus métodos.
     */
    private ModelUser saveUser = null;
    private String[] randomOptions;

    public ViewsQuiz() {/*Void constructor*/}


    /*
     * @LuisaAvila/SebatianSantis
     * @method setUser se encarga de guardar el nombre del usuario como atributo de la clase ModelUser.
     * @param user nombre del usuario que sera incluido como atributo de la clase ModelUser.
     * @see se incluye el método validar respuesta donde el resultado del método keypressed ingresa como parametro y realizar la validación de la respuesta obtenida por el usuario.
     */

    public void setUser(ModelUser user) {
        this.saveUser = user;
    }

    /*
     * @LuisaAvila/SebatianSantis
     * @method getQuestion se encarga de generar la pregunta aleatorea que visualizará el usuario.
     * @see se incluye la clase ObjectQuestion para acceder a sus método y obtener la preguntas,se instancia la clase ModelQuiz para acceder al metodo que genera las preguntas aleatoreas.
     */

    public void getQuestion() {
        level += 1;
        try {
            JSONArray array = ObjectQuestion.returnArray();
            ArrayList<ModelQuestion> questions = ObjectQuestion.mapQuestion(array);

            ModelQuiz quiz = new ModelQuiz(questions);
            question = quiz.getRandomQuestion(level);

        } catch (IOException | ParseException e) {
            log.error(e);
        }
    }

    /*
     * @LuisaAvila/SebatianSantis
     * @method updateData se encarga de obtener las preguntas y la respuestas de la clase ModelQuiz a través del objeto question y los guarda en un arreglo.
     * @see se incluyeel método getQuestion para obtener la pregunta aleatorea y guardarla en el arreglo.
     */
    public void updateData() {
        getQuestion();
        String correct = question.getCorrect();
        String option1 = question.getOption1();
        String option2 = question.getOption2();
        String option3 = question.getOption3();
        randomOptions = new String[]{correct, option1, option2, option3};
    }

    /*
     * @LuisaAvila/SebatianSantis
     * @method validate se encarga de validar de vallidar la respuesta que ha ingresado el usuario, si es correcta aumenta el nivel,el puntaje y la pregunta se actualiza guardandose los datos hasta que termine el juego si es incorrecta se reinicia el juego y se guarda los dtaos.
     * @param recibe el parametro answer que hace referncia a la respuesta del usuario y el index que es la posición de la respuesta para la validación.
     * @see de incluyen los metodos saveNewPlayer para guardar los datos del usuario, viewContext() que es un metodo del menu para mostrarlo.
     */
    public void validate(String answer, Integer index) {

        if (Boolean.TRUE.equals(question.confirm(randomOptions[index]))) {
            score += 1;
            if (score == 5) {
                saveUser.setScore(score);
                saveUser.setWon(true);
                saveUser.saveNewPlayer();
                log.info("! You have won, congratulations");
                menu.setUser(saveUser);
                menu.viewContext();

            } else {
                viewContext();
            }

        } else {
            saveUser.setScore(score);
            saveUser.setWon(false);
            saveUser.saveNewPlayer();
            log.error(
                    "Answer [" + answer + "] incorrect\n" +
                            "you will be sent to the menu where in the [History] section you can see your results"
            );

            menu.setUser(saveUser);
            menu.viewContext();

        }

    }

    /*
     * @LuisaAvila/SebatianSantis
     * @method viewContext() es la inplementación de la interfaz InterfacesView para mostrar la pregunta seleccionada y las respuestas.
     * @see de incluyen los metodos updateData para actualizar la pregunta y sus respuesta que se van a mostrar.
     */

    @Override
    public void viewContext() {

        updateData();

        Arrays.sort(randomOptions);

        log.info(
                "\n\n[" + saveUser.getName() + "] | Level - [" + level + "] | Score: " + score + " | ( If you need exit digit [E] or [e] )" +
                        "\nQuestion: " + question.getQuestion() +
                        "\n\nA] " + randomOptions[0] +
                        "\nB] " + randomOptions[1] +
                        "\nC] " + randomOptions[2] +
                        "\nD] " + randomOptions[3] +
                        "\n\nPut your answer:"
        );

        String answer = getData.nextLine();

        switch (answer.toUpperCase()) {

            case "A" -> validate(answer, 0);
            case "B" -> validate(answer, 1);
            case "C" -> validate(answer, 2);
            case "D" -> validate(answer, 3);
            case "E" -> {
                menu.setUser(saveUser);
                menu.viewContext();
            }

            default -> {
                log.error("[¡Wrong value!] You will be sent to the menu");
                menu.viewContext();
            }

        }

    }


}
