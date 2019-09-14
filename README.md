## Zen-lint: is a plugin with custom rules set for detekt 

The purpose of the plugin is to set up a simple installation and use of [detekt](https://arturbosch.github.io/detekt/index.html) with custom rules set.

### How use?

```groovy
plugins{
    id 'io.example.zen-lint' version '1.0.0'
}

//    optional
zenLint {
    src = files("src/main/kotlin/path/to/some/packages")
}
```
Run the task
```bash
$ ./gradlew zenLint
```

#### Custom rules

The set of user rules is the additional project [*io.example:detektRules*](https://github.com/pavel-evleev/detekt-custom-rules). The plugin itself adds custom rules for detekt.

##### Attention:
 
 If you need to add more custom rules, you should include it into the project [*io.example:detektRules*](https://github.com/pavel-evleev/detekt-custom-rules).
 1. Update [*io.example:detektRules*](https://github.com/pavel-evleev/detekt-custom-rules) with new rules set.
 2. Add new rules to detekt-config.yml  

Examples:
  * [Zen Rules](https://github.com/pavel-evleev/detekt-custom-rules)
  * [Official detekt example](https://arturbosch.github.io/detekt/extensions.html)
  * [Another detekt example](https://medium.com/@vanniktech/writing-your-first-detekt-rule-ee940e56428d)
  

