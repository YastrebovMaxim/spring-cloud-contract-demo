package contracts.pricing

import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    request {
        method PUT()
        url '/pricing/update'
        body([
                car   : value(consumer(anyAlphaNumeric()), producer('BMW')),
                amount: $(stub(number()), test(500)),
                currency: $(stub(regex('[A-Z]{3}')), test('EUR'))
        ])
        headers {
            contentType('application/json')
        }
    }

    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body([
                uuid    : value(anyUuid()),
                car     : value(c('BMW'), producer(anyAlphaNumeric())),
                amount  : 500,
                currency: value(c('EUR'), producer(regex('[A-Z]{3}')))
        ])
    }
}