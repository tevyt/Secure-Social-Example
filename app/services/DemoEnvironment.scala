package services
import models.User
import securesocial.core.RuntimeEnvironment
import securesocial.core.providers.{GoogleProvider, TwitterProvider, FacebookProvider}
import securesocial.core.services.UserService
import services.DemoUserService

import scala.collection.immutable

class DemoEnvironment extends RuntimeEnvironment.Default[User]{
  override val userService: UserService[User] = new DemoUserService()
  override lazy val providers = {
    immutable.ListMap(
      include(
        new FacebookProvider(routes, cacheService,oauth2ClientFor(FacebookProvider.Facebook))
      ),
      include(
        new TwitterProvider(routes,cacheService,oauth1ClientFor(TwitterProvider.Twitter))
      ),
      include(
        new GoogleProvider(routes , cacheService,oauth2ClientFor(GoogleProvider.Google))
      )
    )
  }
}