package eventloop

import java.util.concurrent.*

class EventLoop(private val eventQueue: BlockingQueue<Runnable>) {
    init {
        run()
    }

    private fun run() {
        Thread(Runnable {
            var task = eventQueue.take()
            while (true) {
                task.run()
                task = eventQueue.take()
            }
        }).start()
    }

    fun put(task: Runnable) {
        eventQueue.put(task)
    }
}

fun main() {
    val eventLoop = EventLoop(LinkedBlockingQueue())
    eventLoop.put(Runnable { println("[${Thread.currentThread().name}]task 1") })
    eventLoop.put(Runnable { println("task 2") })
    eventLoop.put(Runnable { println("task 3") })
}