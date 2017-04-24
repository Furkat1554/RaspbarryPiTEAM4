class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?(.$format)?"{}

		"/"(view:   '/overview')
		"/announcement"(view:'/announcement')
		"/control"(view: '/control')
		"/event" (view: '/event')
		"/configure" (view: '/configure')

		"404"(view: '/notFound')
		"500"(view: '/error')
	}
}
