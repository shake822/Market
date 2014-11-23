import com.comtop.mobile.market.Good
import com.comtop.mobile.market.GoodPicture
import com.comtop.mobile.market.User
import com.comtop.mobile.utils.CustomDomainMarshaller
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
//        grails.converters.JSON.registerObjectMarshaller(new CustomDomainMarshaller())
        JSON.registerObjectMarshaller(Date) {
            return it?.format("yyyy-MM-dd HH:mm:ss")
        }
    }

    static {
        grails.converters.JSON.registerObjectMarshaller(Good) {
            return it.properties.findAll { k, v -> k != 'class' }
        }

        grails.converters.JSON.registerObjectMarshaller(GoodPicture) {
            return it.properties.findAll { k, v -> k != 'class' }
        }

        grails.converters.JSON.registerObjectMarshaller(User) {
            return it.properties.findAll { k, v -> k != 'class' }
        }
    }
    def destroy = {
    }
}
