package contracts.pricing

import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    description('should return list of all prices')
    request {
        method 'GET'
        url '/pricing/all'
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
                [
                        "uuid"    : value(anyUuid()),
                        "car"     : "Lada",
                        "amount"  : 1,
                        "currency": "RUB"
                ],
                [
                        "uuid"    : value(anyUuid()),
                        "car"     : "Nissan",
                        "amount"  : 2,
                        "currency": "USD"
                ]
        ])

    }
}