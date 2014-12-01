package io.stupify

class StupifyController {

    def stupifierService

    def index() {
        [:]
    }

    def execute() {
        render stupifierService.stupify(params.data)
    }
}
