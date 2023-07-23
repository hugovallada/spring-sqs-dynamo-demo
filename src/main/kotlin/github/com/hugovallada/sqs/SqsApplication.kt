package github.com.hugovallada.sqs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SqsApplication

fun main(args: Array<String>) {
    runApplication<SqsApplication>(*args)
}

//fun teste() {
//    loop {
//        if (true) {
//            return@loop "A"
//        } else {
//            return@loop "B"
//        }
//    }
//}
//
//fun <T> loop(fn: () -> T) {
//    while (true) {
//        fn()
//    }
//}
