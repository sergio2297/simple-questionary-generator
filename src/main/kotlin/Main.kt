import es.sfernandez.model.Question
import es.sfernandez.model.Questionary

fun main(args : Array<String>) {

    val json = "";

    val question1 : Question = Question();
    val question2 : Question = Question();
    val question3 : Question = Question();
    val question4 : Question = Question();
    question1.image.imagePath = "12";

    val questionary = Questionary.Builder()
        .add(question1)
        .add(question2)
        .add(question3)
        .add(question4)
        .build();

    println(questionary);

}