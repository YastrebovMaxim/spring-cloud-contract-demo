package contracts.pricing

import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    request {
        method GET()
        url('/pricing/calculate') {
            queryParameters {
                parameter car: 'BMW'
            }
        }
        headers {
            contentType('application/json')
        }
    }

    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body([
                uuid    : value(consumer('064731d9-cbd8-4ff7-85be-075af89c97ef'), producer(uuid())),
                car     : 'BMW',
                amount  : value(consumer(123), producer(number())),
                currency: $(client('EUR'), server(regex('[A-Z]{3}')))
        ])
    }
}