import com.voc.gradle.plugin.core.DevType


plugins {
  id("com.voc.devtools")
}

description = "Common Dependencies"

devtools {
  type(DevType.BOM)
}

dependencyManagement {
  dependencies {

    dependencySet("$group:$version") {
      /* 自动包含所有的子项目 */
      subprojects.forEach {
        entry(it.name)
      }
    }
  }
}

subprojects {
  devtools {
    type(DevType.LIB)
    lombok(true)
    junit(true)
  }
}



