/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.xyz.common.security.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 封装多个注解
 *
 * @author admin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
@ComponentScan
public @interface EnableCrssFeignClients {

	/**
	 * Alias for the {@link #basePackages()} attribute. Allows for more concise annotation declarations e.g.: {@code
	 *
	 * @return the array of 'basePackages'.
	 * @ComponentScan("org.my.pkg")} instead of {@code @ComponentScan(basePackages="org.my.pkg")}.
	 */
	String[] value() default {};

	/**
	 * Base packages to scan for annotated components.
	 * <p>
	 * {@link #value()} is an alias for (and mutually exclusive with) this attribute.
	 * <p>
	 * Use {@link #basePackageClasses()} for a type-safe alternative to String-based package names.
	 *
	 * @return the array of 'basePackages'.
	 */
	String[] basePackages() default {"com.cloudminds.crss"};

	/**
	 * Type-safe alternative to {@link #basePackages()} for specifying the packages to scan for annotated components. The
	 * package of each class specified will be scanned.
	 * <p>
	 * Consider creating a special no-op marker class or interface in each package that serves no purpose other than being
	 * referenced by this attribute.
	 *
	 * @return the array of 'basePackageClasses'.
	 */
	Class<?>[] basePackageClasses() default {};

	/**
	 * A custom <code>@Configuration</code> for all feign clients. Can contain override <code>@Bean</code> definition for
	 * the pieces that make up the client, for instance {@link feign.codec.Decoder}, {@link feign.codec.Encoder}, {@link
	 * feign.Contract}.
	 *
	 * @see FeignClientsConfiguration for the defaults
	 */
	Class<?>[] defaultConfiguration() default {};

	/**
	 * List of classes annotated with @FeignClient. If not empty, disables classpath scanning.
	 *
	 * @return
	 */
	Class<?>[] clients() default {};
}
