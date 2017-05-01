grails {
	plugin {
		springsecurity {
			authority.className = 'test.Role'
			cacheUsers = true
			gsp {
				layoutAuth = 'application'
				layoutDenied = 'template'
			}
			logout.postOnly = false
			roleHierarchy = 'ROLE_ADMIN > ROLE_USER'
			userLookup {
				authorityJoinClassName = 'test.UserRole'
				userDomainClassName = 'test.User'
			}

			controllerAnnotations.staticRules = [
				[pattern: '/error',          access: 'permitAll'],
				[pattern: '/index',          access: 'permitAll'],
				[pattern: '/shutdown',       access: 'permitAll'],
				[pattern: '/assets/**',      access: 'permitAll'],
				[pattern: '/**/js/**',       access: 'permitAll'],
				[pattern: '/**/css/**',      access: 'permitAll'],
				[pattern: '/**/images/**',   access: 'permitAll'],
				[pattern: '/**/favicon.ico', access: 'permitAll'],

				[pattern: '/**',              access: 'ROLE_USER'],
				[pattern: '/configure',       access: 'ROLE_ADMIN'],
				[pattern: '/dbconsole/**',    access: 'ROLE_ADMIN'],
				[pattern: '/securityinfo',    access: 'ROLE_ADMIN'],
				[pattern: '/securityinfo/**', access: 'ROLE_ADMIN']
			]

			filterChain.chainMap = [
				[pattern: '/assets/**',      filters: 'none'],
				[pattern: '/**/js/**',       filters: 'none'],
				[pattern: '/**/css/**',      filters: 'none'],
				[pattern: '/**/images/**',   filters: 'none'],
				[pattern: '/**/favicon.ico', filters: 'none'],
				[pattern: '/**',             filters: 'JOINED_FILTERS']
			]

			secureChannel.definition = [
				[pattern: '/testsecure/**',   access: 'REQUIRES_SECURE_CHANNEL'],
				[pattern: '/testinsecure/**', access: 'REQUIRES_INSECURE_CHANNEL'],
				[pattern: '/testany/**',      access: 'ANY_CHANNEL']
			]
		}
	}
}
