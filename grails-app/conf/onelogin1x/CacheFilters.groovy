package onelogin1x

class CacheFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                // response.setHeader('Expires', '-1')
            }
            after = {
                
            }
            afterView = {
                
            }
        }
    }
    
}
