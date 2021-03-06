
//
// Copyright (c) 2012 Electric Cloud.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the "Software"), to
// deal in the Software without restriction, including without limitation the
// rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
// sell copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE
//

package com.electriccloud.maven.javac2;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.jfrog.maven.annomojo.annotations.*;

import java.io.File;

/**
 * Run @NotNull bytecode instrumentation on
 * ${project.build.testOutputDirectory}.
 */
@MojoGoal("testInstrument")
@MojoPhase("process-test-classes")
@MojoRequiresDependencyResolution("test")
@MojoRequiresOnline(false)
@MojoRequiresProject(true)
public class TestInstrumentMojo
    extends Javac2MojoSupport
{

    //~ Instance fields --------------------------------------------------------

    @MojoParameter(
        expression = "${project.build.testOutputDirectory}",
        required   = true,
        readonly   = true
    )
    private File m_outputDirectory;

    //~ Methods ----------------------------------------------------------------

    @Override public void execute()
        throws MojoExecutionException
    {

        try {
            instrumentNotNull(m_outputDirectory, m_project.getTestClasspathElements());
        }
        catch (DependencyResolutionRequiredException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }
}
