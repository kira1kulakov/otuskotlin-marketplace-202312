package ru.otus.otuskotlin.social.moodnow.common.exceptions

import ru.otus.otuskotlin.social.moodnow.common.enums.AppCommand

class UnknownRestCommandException(command: AppCommand) :
    Throwable("Wrong command $command at mapping toTransport stage")