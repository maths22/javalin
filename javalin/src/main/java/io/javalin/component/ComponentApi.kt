package io.javalin.component

import io.javalin.http.Context
import java.util.function.Supplier
import org.jetbrains.annotations.ApiStatus.Experimental

open class ComponentAccessor<COMPONENT : Any?> @JvmOverloads constructor(
    val type: Class<COMPONENT>,
    val id: String = type.name
)

@Deprecated("Experimental")
@Experimental
class ParametrizedComponentAccessor<COMPONENT : Any?, PARAMETERS> @JvmOverloads constructor(
    type: Class<COMPONENT>,
    val defaultValues: Supplier<PARAMETERS>,
    id: String = type.name
) : ComponentAccessor<COMPONENT>(type, id)

fun interface ComponentResolver<COMPONENT : Any?> {
    fun resolve(ctx: Context?): COMPONENT
}

@Deprecated("Experimental")
@Experimental
fun interface ParametrizedComponentResolver<COMPONENT : Any?, CFG> {
    fun resolve(config: CFG, ctx: Context?): COMPONENT
}

class ComponentNotFoundException(
    accessor: ComponentAccessor<*>
) : IllegalStateException("No component resolver registered for ${accessor.id}")
