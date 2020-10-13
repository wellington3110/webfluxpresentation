package br.com.wellington.webfluxpresentation

import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.*
import reactor.core.scheduler.*

@RestController
class HeavyController {

  fun doHeavyProcess() {
    println("running other spring boot application rs")
  }

  @PostMapping("/heavy-process")
  fun heavyProcess(): Mono<ResponseEntity<Nothing>> {
    return Mono
      .defer{ Mono.justOrEmpty(::doHeavyProcess) }
      .subscribeOn(Schedulers.parallel())
      .map { ResponseEntity.ok().build<Nothing>() }
  }
}
