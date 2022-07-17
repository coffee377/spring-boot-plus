import com.voc.gradle.plugin.core.DevType

plugins {
  id("com.voc.devtools")
}

dependencyManagement {
  dependencies {
    dependencySet("$group:$version") {
      entry("common-api")
    }
  }
}

devtools {
  type(DevType.BOM)
}

description = "Common Dependencies"

dependencies {
}

subprojects {
  devtools {
    type(DevType.LIB)
    lombok(true)
    junit(true)
  }
}
