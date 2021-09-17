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

package com.cloudminds.crss.codegen.controller;

import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudminds.crss.codegen.entity.GenConfig;
import com.cloudminds.crss.codegen.service.SysGeneratorService;
import com.demo.xyz.common.core.util.R;

import cn.hutool.core.io.IoUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedOutputStream;

/**
 * 代码生成器
 *
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class SysGeneratorController {
    private final SysGeneratorService sysGeneratorService;

    /**
     * 列表
     *
     * @param tableName 参数集
     * @return 数据库表
     */
    @GetMapping("/page")
    public R<IPage> list(Page page, String tableName) {
        return new R<>(sysGeneratorService.queryPage(page, tableName));
    }

    /**
     * 生成代码
     */
    @PostMapping("/test")
    @SneakyThrows
    public void code(@RequestBody GenConfig genConfig) {
        byte[] data = sysGeneratorService.generatorCode(genConfig);

//        response.reset();
//        response.setHeader("Content-Disposition",
//                String.format("attachment; filename=%s.zip", genConfig.getTableName()));
//        response.addHeader("Content-Length", "" + data.length);
//        response.setContentType("application/octet-stream; charset=UTF-8");

        //IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);

        String fileName = genConfig.getTableName();
        BufferedOutputStream outputStream = FileUtil.getOutputStream("d:/crssgen/" + fileName + ".zip");
        IoUtil.write(outputStream, true, data);
    }
}
