package com.voc.codegen;

import com.google.auto.service.AutoService;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.CodegenOperation;
import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.languages.AbstractTypeScriptClientCodegen;
import org.openapitools.codegen.model.ModelMap;
import org.openapitools.codegen.model.OperationMap;
import org.openapitools.codegen.model.OperationsMap;

import java.util.List;

@AutoService(CodegenConfig.class)
public class UISpherePageGenerator extends AbstractTypeScriptClientCodegen {

    /**
     * Configures the type of generator.
     *
     * @return the CodegenType for this generator
     * @see org.openapitools.codegen.CodegenType
     */
    public CodegenType getTag() {
        return CodegenType.OTHER;
    }

    /**
     * Configures a friendly name for the generator.  This will be used by the generator
     * to select the library with the -g flag.
     *
     * @return the friendly name for the generator
     */
    public String getName() {
        return "page";
    }

    /**
     * Provides an opportunity to inspect and modify operation data before the code is generated.
     */
    @Override
    public OperationsMap postProcessOperationsWithModels(OperationsMap objs, List<ModelMap> allModels) {

        // to try debugging your code generator:
        // set a break point on the next line.
        // then debug the JUnit test called LaunchGeneratorInDebugger

        OperationsMap results = super.postProcessOperationsWithModels(objs, allModels);

        OperationMap ops = results.getOperations();
        List<CodegenOperation> opList = ops.getOperation();

        // iterate over the operation and perhaps modify something
        for(CodegenOperation co : opList){
            // example:
            // co.httpMethod = co.httpMethod.toLowerCase();
        }

        return results;
    }

    /**
     * Returns human-friendly help for the generator.  Provide the consumer with help
     * tips, parameters here
     *
     * @return A string value for the help message
     */
    public String getHelp() {
        return "Generates a page.";
    }


    public UISpherePageGenerator() {
        super();
        // set the output folder here
        outputFolder = "generated-code/pages";

//        /**
//         * Models.  You can write model files using the modelTemplateFiles map.
//         * if you want to create one template for file, you can do so here.
//         * for multiple files for model, just put another entry in the `modelTemplateFiles` with
//         * a different extension
//         */
//        modelTemplateFiles.put(
//                "model.mustache", // the template to use
//                ".sample");       // the extension for each file to write
//
        /**
         * Api classes.  You can write classes for each Api file with the apiTemplateFiles map.
         * as with models, add multiple entries with different extensions for multiple files per
         * class
         */
        apiTemplateFiles.put(
                "api.hbs",   // the template to use
                ".ts");       // the extension for each file to write
        /**
         * Template Location.  This is the location which templates will be read from.  The generator
         * will use the resource stream to attempt to read the templates.
         */
        templateDir = "pages";

//
//        /**
//         * Api Package.  Optional, if needed, this can be used in templates
//         */
//        apiPackage = "org.openapitools.api";
//
//        /**
//         * Model Package.  Optional, if needed, this can be used in templates
//         */
//        modelPackage = "org.openapitools.model";
//
//        /**
//         * Reserved words.  Override this with reserved words specific to your language
//         */
//        reservedWords = new HashSet<String>(
//                Arrays.asList(
//                        "sample1",  // replace with static values
//                        "sample2")
//        );
//
//        /**
//         * Additional Properties.  These values can be passed to the templates and
//         * are available in models, apis, and supporting files
//         */
//        additionalProperties.put("apiVersion", apiVersion);
//
//        /**
//         * Supporting Files.  You can write single files for the generator with the
//         * entire object tree available.  If the input file has a suffix of `.mustache
//         * it will be processed by the template engine.  Otherwise, it will be copied
//         */
//        supportingFiles.add(new SupportingFile("myFile.mustache",   // the input template or file
//                "",                                                       // the destination folder, relative `outputFolder`
//                "myFile.sample")                                          // the output file
//        );
//
//        /**
//         * Language Specific Primitives.  These types will not trigger imports by
//         * the client generator
//         */
//        languageSpecificPrimitives = new HashSet<String>(
//                Arrays.asList(
//                        "Type1",      // replace these with your types
//                        "Type2")
//        );
    }
}
