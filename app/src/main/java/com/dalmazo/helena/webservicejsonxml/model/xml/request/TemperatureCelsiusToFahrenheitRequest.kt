package com.dalmazo.helena.webservicejsonxml.model.xml.request

import org.simpleframework.xml.*

@Root(name = "soap:Envelope")
@NamespaceList(
    Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
    Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
    Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
)
class TemperatureCelsiusToFahrenheitRequest(
    @field:Path("soap:Body")
    @field:Element(name = "CelsiusToFahrenheit")
    @field:Namespace(reference = "https://www.w3schools.com/xml/")
    var request: Request = Request()
) {
    class Request(
        @field:Element(name = "Celsius") var celsius: String = ""
    )
}