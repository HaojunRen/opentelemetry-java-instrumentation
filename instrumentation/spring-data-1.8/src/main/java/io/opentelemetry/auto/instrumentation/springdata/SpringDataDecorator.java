// This file includes software developed at SignalFx

package io.opentelemetry.auto.instrumentation.springdata;

import io.opentelemetry.OpenTelemetry;
import io.opentelemetry.auto.api.MoreTags;
import io.opentelemetry.auto.decorator.ClientDecorator;
import io.opentelemetry.trace.Span;
import io.opentelemetry.trace.Tracer;
import java.lang.reflect.Method;

public final class SpringDataDecorator extends ClientDecorator {
  public static final SpringDataDecorator DECORATOR = new SpringDataDecorator();

  public static final Tracer TRACER = OpenTelemetry.getTracerFactory().get("io.opentelemetry.auto");

  private SpringDataDecorator() {}

  @Override
  protected String service() {
    return null;
  }

  @Override
  protected String[] instrumentationNames() {
    return new String[] {"spring-data"};
  }

  @Override
  protected String spanType() {
    return null;
  }

  @Override
  protected String component() {
    return "spring-data";
  }

  public Span onOperation(final Span span, final Method method) {
    assert span != null;
    assert method != null;

    if (method != null) {
      span.setAttribute(MoreTags.RESOURCE_NAME, spanNameForMethod(method));
    }
    return span;
  }
}