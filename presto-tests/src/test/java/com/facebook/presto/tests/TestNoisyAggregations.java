/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.tests;

import com.facebook.presto.testing.QueryRunner;
import com.facebook.presto.tests.tpch.TpchQueryRunnerBuilder;
import org.testng.annotations.Test;

public class TestNoisyAggregations
        extends AbstractTestQueryFramework
{
    @Override
    protected QueryRunner createQueryRunner()
            throws Exception
    {
        return TpchQueryRunnerBuilder.builder().build();
    }

    @Test
    public void testNoisyCountGaussianZeroNoiseScaleVsNormalCount()
    {
        assertQuery("SELECT noisy_count_gaussian(1, 0) FROM lineitem", "SELECT count(*) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(linenumber, 0) FROM lineitem", "SELECT count(linenumber) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(orderkey, 0) FROM lineitem", "SELECT count(orderkey) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(quantity, 0) FROM lineitem", "SELECT count(quantity) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(linestatus, 0) FROM lineitem", "SELECT count(linestatus) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(shipdate, 0) FROM lineitem", "SELECT count(shipdate) FROM lineitem");
    }

    @Test
    public void testNoisyCountGaussianZeroNoiseScaleRandomSeedVsNormalCount()
    {
        assertQuery("SELECT noisy_count_gaussian(1, 0, 10) FROM lineitem", "SELECT count(*) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(linenumber, 0, 10) FROM lineitem", "SELECT count(linenumber) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(orderkey, 0, 10) FROM lineitem", "SELECT count(orderkey) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(quantity, 0, 10) FROM lineitem", "SELECT count(quantity) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(linestatus, 0, 10) FROM lineitem", "SELECT count(linestatus) FROM lineitem");
        assertQuery("SELECT noisy_count_gaussian(shipdate, 0, 10) FROM lineitem", "SELECT count(shipdate) FROM lineitem");
    }
}
