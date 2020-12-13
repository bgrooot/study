```
➜  ~ kotlin
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.intellij.util.ReflectionUtil (file:/usr/local/Cellar/kotlin/1.4.21/libexec/lib/kotlin-compiler.jar) to method java.util.ResourceBundle.setParent(java.util.ResourceBundle)
WARNING: Please consider reporting this to the maintainers of com.intellij.util.ReflectionUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
Welcome to Kotlin version 1.4.21 (JRE 15.0.1+9)
Type :help for help, :quit for quit
>>> 1 in 1..3
res0: kotlin.Boolean = true
>>> (1..3).toList()
res1: kotlin.collections.List<kotlin.Int> = [1, 2, 3]
>>> 1 in 3 downTo 1
res2: kotlin.Boolean = true
>>> 1 in 1 until 3
res3: kotlin.Boolean = true
>>> 3 in 1 until 3
res4: kotlin.Boolean = false
>>> 2 in 1..3
res5: kotlin.Boolean = true
>>> 2 !in 1..3
res6: kotlin.Boolean = false
>>> 'x' in 'a'..'z'
res7: kotlin.Boolean = true
>>>
```
