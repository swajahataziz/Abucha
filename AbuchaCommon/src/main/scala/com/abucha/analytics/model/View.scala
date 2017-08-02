package com.abucha.analytics.model

class View[A <: Component]  (val path: List[_ <: Component], val root: Boolean, val folders: List[_ <: Component], files: List[A]) {

}
