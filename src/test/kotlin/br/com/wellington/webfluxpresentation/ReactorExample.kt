package br.com.wellington.webfluxpresentation

import org.junit.jupiter.api.Test
import reactor.core.publisher.*
import reactor.core.scheduler.*

class ReactorExample {

	@Test
	fun example_01() {
		val publisher = Flux.fromArray(arrayOf("Goku", ">", "Naruto"))
		publisher.subscribe { value -> print(value) /* consumer */ }
		// Imprime: Goku>Naruto
	}

	@Test
	fun example_02() {
		val publisher = Flux.fromArray(arrayOf("Goku", ">", "Naruto"))
		publisher.map(String::toUpperCase)
				.map { value -> if(value == ">") "<" else value }
				.subscribe { value -> print(value) /* consumer */ }
				// Imprime: GOKU<NARUTO
	}

	@Test
	fun example_03() {
		val monoPublisher = Mono.just("Goku is the best!")
		monoPublisher.subscribeOn(Schedulers.parallel())
				.subscribe { print("[${Thread.currentThread().name}] $it") }
		println("[${Thread.currentThread().name}] Who is the best?")
		// Imprime: [main] Who is the best?
		//		    [parallel-1] Goku is the best!
	}

	@Test
	fun example_04() {
		val oi = Flux.fromArray(arrayOf(1, 2, 3))
				.flatMap {
					println("[${Thread.currentThread().name}] [1] flat map operation")
					Flux.fromArray(arrayOf(it + 1))
				}
				.flatMap {
					println("[${Thread.currentThread().name}] [2] flat map operation")
					Flux.fromArray(arrayOf(it + 1)).subscribeOn(Schedulers.elastic())
				}
				.map {
					println("[${Thread.currentThread().name}] map operation")
					"num: $it"
				}
		oi.subscribe {
			println("[${Thread.currentThread().name}] $it")
		}
	}

}
