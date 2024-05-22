package ru.otus.otuskotlin.social.moodnow.app.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.logging.common.MpLoggerProvider
import ru.otus.otuskotlin.social.moodnow.logging.logback.mpLoggerLogback

@Suppress("unused")
@Configuration
class MainConfig {

    @Bean
    fun loggerProvider(): MpLoggerProvider = MpLoggerProvider { mpLoggerLogback(it) }

    @Bean
    fun corSettings(): AppCorSettings = AppCorSettings(loggerProvider = loggerProvider())

    @Bean
    fun processor(corSettings: AppCorSettings) = AppChatterProcessor(appCorSettings = corSettings)

    @Bean
    fun appSettings(
        corSettings: AppCorSettings,
        processor: AppChatterProcessor
    ) = AppSettings(
        corSettings = corSettings,
        processor = processor
    )
}