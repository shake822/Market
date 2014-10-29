import com.comtop.mobile.market.util.FileUtils

// Place your Spring DSL code here
beans = {
	fileUtils(FileUtils)
	{ grailsApplication = ref('grailsApplication') }
}
