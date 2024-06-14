package ru.otus.otuskotlin.social.moodnow.app.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.common.repo.IRepoChatter
import ru.otus.otuskotlin.social.moodnow.logging.common.MpLoggerProvider
import ru.otus.otuskotlin.social.moodnow.logging.logback.mpLoggerLogback
import ru.otus.otuskotlin.social.moodnow.repo.inmemory.ChatterRepoInMemory
import ru.otus.otuskotlin.social.moodnow.repo.stubs.ChatterRepoStub

@Suppress("unused")
@Configuration
class MainConfig {
    protected val uuidNew = "00000000-0000-0000-0000-000000000000"

    @Bean
    fun loggerProvider(): MpLoggerProvider = MpLoggerProvider { mpLoggerLogback(it) }

    @Bean
    fun testRepo(): IRepoChatter = ChatterRepoInMemory(randomUuid = { uuidNew })

    @Bean
    fun prodRepo(): IRepoChatter = ChatterRepoInMemory(randomUuid = { uuidNew })

    @Bean
    fun stubRepo(): IRepoChatter = ChatterRepoStub()

    @Bean
    fun corSettings(): AppCorSettings = AppCorSettings(
        loggerProvider = loggerProvider(),
        repoTest = testRepo(),
        repoProd = prodRepo(),
        repoStub = stubRepo(),
    )

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