package com.dalmazo.helena.webservicejsonxml.model.xml.response

import org.simpleframework.xml.*

@Root(name = "soap:Envelope")
@NamespaceList(
    Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
    Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
    Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
)
class TemperatureCelsiusToFahrenheitResponse(
    @field:Path("soap:Body")
    @field:Element(name = "CelsiusToFahrenheitResponse")
    @field:Namespace(reference = "https://www.w3schools.com/xml/")
    var response: Response = Response()
) {
    class Response(
        @field:Element(name = "CelsiusToFahrenheitResult") var result: String = ""
    )
}