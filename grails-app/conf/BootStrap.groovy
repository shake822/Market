import com.comtop.mobile.market.Good
import com.comtop.mobile.utils.CustomDomainMarshaller

class BootStrap {

    def init = { servletContext ->
//        grails.converters.JSON.registerObjectMarshaller(new CustomDomainMarshaller())
    }

    static {
        grails.converters.JSON.registerObjectMarshaller(Good) {
            return it.properties.findAll {k,v -> k != 'class'}
        }
    }

    def destroy = {
    }
}
