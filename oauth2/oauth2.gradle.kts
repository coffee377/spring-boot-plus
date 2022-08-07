
dependencyManagement {
  dependencies {
    dependencySet("$group:$version") {
      subprojects.forEach {
        entry(it.name)
      }
    }
  }
}
