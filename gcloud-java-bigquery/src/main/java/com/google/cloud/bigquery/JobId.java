/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.bigquery;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.api.services.bigquery.model.JobReference;

import java.io.Serializable;
import java.util.Objects;

/**
 * Google BigQuery Job identity.
 */
public final class JobId implements Serializable {

  private static final long serialVersionUID = 1225914835379688976L;

  private final String project;
  private final String job;

  /**
   * Returns project's user-defined id.
   */
  public String project() {
    return project;
  }

  /**
   * Returns the job's user-defined id.
   */
  public String job() {
    return job;
  }

  private JobId(String project, String job) {
    this.project = project;
    this.job = job;
  }

  /**
   * Creates a job identity given project's and job's user-defined id.
   */
  public static JobId of(String project, String job) {
    return new JobId(checkNotNull(project), checkNotNull(job));
  }

  /**
   * Creates a job identity given only its user-defined id.
   */
  public static JobId of(String job) {
    return new JobId(null, checkNotNull(job));
  }

  @Override
  public boolean equals(Object obj) {
    return obj == this
        || obj instanceof JobId
        && Objects.equals(toPb(), ((JobId) obj).toPb());
  }

  @Override
  public int hashCode() {
    return Objects.hash(project, job);
  }

  @Override
  public String toString() {
    return toPb().toString();
  }

  JobReference toPb() {
    return new JobReference().setProjectId(project).setJobId(job);
  }

  static JobId fromPb(JobReference jobRef) {
    return new JobId(jobRef.getProjectId(), jobRef.getJobId());
  }
}
