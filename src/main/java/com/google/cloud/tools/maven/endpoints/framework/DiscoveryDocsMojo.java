/*
 * Copyright (c) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.google.cloud.tools.maven.endpoints.framework;

import com.google.api.server.spi.tools.GetDiscoveryDocAction;
import java.io.File;
import java.util.List;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

/** Goal which generates discovery docs. */
@Mojo(
    name = "discoveryDocs",
    requiresDependencyResolution = ResolutionScope.COMPILE,
    defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
public class DiscoveryDocsMojo extends AbstractEndpointsWebAppMojo {

  /** Additional parameters to pass to Open API generation action. */
  @Parameter(property = "endpoints.discovery.additionalParameters", required = false)
  private String discoveryAdditionalParameters;

  /** Output directory for discovery docs. */
  @Parameter(
      defaultValue = "${project.build.directory}/discovery-docs",
      property = "endpoints.discovery.docDir",
      required = true)
  private File discoveryDocDir;

  @Override
  protected String getActionName() {
    return GetDiscoveryDocAction.NAME;
  }

  @Override
  protected File getOutputDirectory() {
    return discoveryDocDir;
  }

  @Override
  protected String getAdditionalParameters() {
    return discoveryAdditionalParameters;
  }

  @Override
  protected void addSpecificParameters(List<String> params) {
    // no specific parameters
  }
}
