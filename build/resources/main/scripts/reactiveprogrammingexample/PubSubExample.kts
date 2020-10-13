import java.time.LocalDateTime.*
import java.util.*

class Publisher<T> {

    private val consumers = mutableListOf<Consumer<T>>()

    fun subscribe(consumer: Consumer<T>) {
        consumers.add(consumer)
    }

    fun publish(value: T) = consumers.forEach { consume -> consume.consume(value) }
}

interface Consumer<T> {
    fun consume(command: T)
}

fun menu() {
    println("""
---------------------------------------------------------------
Menu
---------------------------------------------------------------
1. Imprima o nome do melhor marketplace do mundo.
2. Imprima o nome do time mais vezes campeão catarinense.
3. Sair.
---------------------------------------------------------------
""".trimIndent())
}

class CommandLogger : Consumer<Int> {

    private val logs = mutableListOf<String>()

    override fun consume(command: Int) = log(command)

    private fun log(command: Int) {
        logs.add("[${now()}] command $command")
    }

    fun printLogs() = logs.forEach(::println)
}

class CommandExecutor : Consumer<Int> {
    override fun consume(command: Int) {
        val resp = when (command) {
            1 -> "Amaz... zueira é o Mercado Livre HAUAHUHUAAHUA"
            2 -> "Figueira né feio"
            3 -> throw FinishProgram()
            else -> "Essa opção não existe feio"
        }
        println("Resposta: $resp")
    }
}

class FinishProgram : RuntimeException()

fun clearConsole() = print("\u001b[H\u001b[2J")

fun main() {
    clearConsole()
    val publisher = Publisher<Int>()
    val commandLogger = CommandLogger()
    val commandExecutor = CommandExecutor()
    publisher.subscribe(commandLogger)
    publisher.subscribe(commandExecutor)
    try {
        val read = Scanner(System.`in`)
        while (true) {
            menu()
            println("Digite a opção desejada: ")
            val command = read.nextInt()
            clearConsole()
            publisher.publish(command)
        }
    } catch (e: FinishProgram) {
        println("Comandos executados: ")
        commandLogger.printLogs()
        print("Encerrando o programa!")
    }
}

main()
