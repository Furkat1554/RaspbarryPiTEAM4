import test.Role
import test.User
import test.UserRole

class BootStrap {

	def init = {
		def admin = new User(username: 'admin', password: 'password', role: 'ROLE_ADMIN').save(failOnError: true)
		def user = new User(username: 'user', password: 'password', role: 'ROLE_USER').save(failOnError: true)

		def roleUser = new Role(authority: 'ROLE_USER').save(failOnError: true)
		def roleAdmin = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)


		UserRole.create admin, roleAdmin
		UserRole.create user, roleUser


		User.withSession {
			it.flush()
			it.clear()
		}

	}
}
