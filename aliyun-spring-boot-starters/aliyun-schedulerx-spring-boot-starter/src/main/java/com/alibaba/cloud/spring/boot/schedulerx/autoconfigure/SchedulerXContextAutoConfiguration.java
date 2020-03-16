/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.spring.boot.schedulerx.autoconfigure;

import com.alibaba.cloud.context.edas.AliCloudEdasSdk;
import com.alibaba.cloud.context.scx.AliCloudScxInitializer;
import com.alibaba.cloud.spring.boot.context.autoconfigure.EdasContextAutoConfiguration;
import com.alibaba.cloud.spring.boot.context.env.AliCloudProperties;
import com.alibaba.cloud.spring.boot.context.env.EdasProperties;
import com.alibaba.cloud.spring.boot.schedulerx.env.SchedulerXProperties;
import com.alibaba.edas.schedulerx.SchedulerXClient;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaolongzuo
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(name = "com.alibaba.alicloud.scx.ScxAutoConfiguration")
@ConditionalOnProperty(name = "alibaba.cloud.scx.enabled", matchIfMissing = true)
@EnableConfigurationProperties(SchedulerXProperties.class)
@ImportAutoConfiguration(EdasContextAutoConfiguration.class)
public class SchedulerXContextAutoConfiguration {

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public SchedulerXClient schedulerXClient(AliCloudProperties aliCloudProperties,
											 EdasProperties edasProperties,
											 SchedulerXProperties scxProperties,
											 AliCloudEdasSdk aliCloudEdasSdk) {
        return AliCloudScxInitializer.initialize(aliCloudProperties, edasProperties,
                scxProperties, aliCloudEdasSdk);
    }

}
