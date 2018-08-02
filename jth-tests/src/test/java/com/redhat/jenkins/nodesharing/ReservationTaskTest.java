/*
 * The MIT License
 *
 * Copyright (c) Red Hat, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.redhat.jenkins.nodesharing;

import com.redhat.jenkins.nodesharing.utils.BlockingBuilder;
import com.redhat.jenkins.nodesharingbackend.Pool;
import hudson.model.FreeStyleProject;
import hudson.model.Label;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReservationTaskTest {
    @Rule public NodeSharingJenkinsRule j = new NodeSharingJenkinsRule();

    @Test
    public void linkIsClickable() throws Exception {
        j.addSharedNodeCloud(Pool.getInstance().getConfigRepoUrl());
        j.singleJvmGrid(j.jenkins);

        BlockingBuilder<FreeStyleProject> bb = j.getBlockingProject("solaris11");
        FreeStyleProject p = bb.getProject();
        p.scheduleBuild2(0).getStartCondition().get();
        p.scheduleBuild2(0);

//        assertThat(j.getActiveReservations().size(), equalTo(1));
//        assertThat(j.getQueuedReservations().size(), equalTo(1));

        j.interactiveBreak();
    }
}
