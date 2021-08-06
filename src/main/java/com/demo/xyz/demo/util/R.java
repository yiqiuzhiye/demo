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

package com.demo.xyz.demo.util;

import com.cloudminds.crss.common.core.constant.ApiRetCodeEnum;
import com.cloudminds.crss.common.core.constant.ApiRetCodeHolder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5663717945191277844L;

	@Getter
	@Setter
	private int code = ApiRetCodeEnum.CODE_SUCCESS;

	@Getter
	@Setter
	private String message = ApiRetCodeEnum.MESSAGE_SUCCESS;

	@Getter
	@Setter
	private String uId;

	@Getter
	@Setter
	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.message = msg;
	}

	public R(Throwable e) {
		super();
		this.message = e.getMessage();
		this.code = ApiRetCodeHolder.getAppCodeException();
	}

	public R(Integer code, String message) {

		super();

		this.message = message;
		if (code == null) {
			this.code = ApiRetCodeHolder.getAppCodeException();
		} else {
			this.code = code;
		}
		if (StringUtils.isBlank(message)) {
			this.message = ApiRetCodeHolder.getMessage(this.code);
		}
	}

	public R(Integer code, String message, T data) {

		super();

		this.message = message;
		if (code == null) {
			this.code = ApiRetCodeHolder.getAppCodeException();
		} else {
			this.code = code;
		}
		if (StringUtils.isBlank(message)) {
			this.message = ApiRetCodeHolder.getMessage(this.code);
		}

		if (data != null) {
			this.data = data;
		}
	}

	public R(String message) {
		super();
		this.message = message;
		this.code = ApiRetCodeHolder.getAppCodeException();
	}

}