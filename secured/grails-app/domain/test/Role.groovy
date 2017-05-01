package test

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
class Role implements Serializable {

	private static final long serialVersionUID = 1

	String authority

	static constraints = {
		authority blank: false, unique: true
	}

	static mapping = {
		cache true
	}

	@Override
	String toString() {
		return authority
	}
}
