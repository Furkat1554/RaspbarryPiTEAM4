class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?(.$format)?"{}

		"/"(view:   '/overview')
		"/post"(view:'/post')
		"/control"(view: '/control')
		"/event" (view: '/event')
		"/configure" (view: '/configure')

		"404"(view: '/notFound')
		"500"(view: '/error')
	}
}
