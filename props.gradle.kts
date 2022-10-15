class Props {
  private val properties: java.util.Properties = java.util.Properties()

  init {
    val root = rootProject.projectDir.parentFile.absolutePath
    val file = file("${root}/gradle.properties")
    this.properties.load(FileInputStream(file))
  }

  operator fun get(name: String, defaultValue: String): String {
    val value = properties.getProperty(name)
    if (!value.isNullOrEmpty()) {
      return value
    }
    return defaultValue
  }
}
