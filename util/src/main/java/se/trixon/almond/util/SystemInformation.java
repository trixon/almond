/*
 * Copyright 2023 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.almond.util;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import oshi.SystemInfo;
import oshi.util.FormatUtil;

/**
 *
 * @author Patrik Karlström
 */
public class SystemInformation {

    private String mData;

    public static void main(String[] args) {
        SystemInformation si = new SystemInformation();
        System.out.println(si.generateSystemInformation());
    }

    public void copy() {
        SystemHelper.copyToClipboard(mData);
    }

    public String generateSystemInformation() {
        var systemInfo = new SystemInfo();
        var hw = systemInfo.getHardware();
        var os = systemInfo.getOperatingSystem();

        var hwItems = new ArrayList<String>();

        hwItems.add("%s %s (%s)".formatted(
                hw.getComputerSystem().getManufacturer(),
                hw.getComputerSystem().getModel(),
                FormatUtil.formatHertz(hw.getProcessor().getMaxFreq())
        ));
        hwItems.add(hw.getProcessor().getProcessorIdentifier().getName());
        hwItems.add(hw.getProcessor().getProcessorIdentifier().getIdentifier());
//        hwItems.add(String.format("Available memory %s/%s", FormatUtil.formatBytes(hw.getMemory().getAvailable()), FormatUtil.formatBytes(hw.getMemory().getTotal())));
        hw.getGraphicsCards().forEach(graphicsCard -> {
            hwItems.add("%s %s".formatted(graphicsCard.getName(), graphicsCard.getVendor()));
        });

        hw.getDiskStores().forEach(diskStore -> {
            hwItems.add(diskStore.toString());
        });

        hwItems.add(hw.getMemory().toString());
        hwItems.add(hw.getMemory().getVirtualMemory().toString());

        var osItems = new ArrayList<String>();
        osItems.add(os.toString());

        var sb = new StringBuilder();
        sb.append(">HARDWARE").append("\n");
        sb.append(String.join("\n", hwItems));

        sb.append("\n\n>OS").append("\n");
        sb.append(String.join("\n", osItems));

        sb.append("\n\n>ENV").append("\n");
        sb.append(String.join("\n", getSystemProperties()));

        return mData = sb.toString();
    }

    private ArrayList<String> getSystemProperties() {
        String[] keys = new String[]{
            "user.country",
            "user.home",
            "user.language",
            "user.name",
            "user.timezone",
            "",
            "os.arch",
            "os.name",
            "os.version",
            "",
            "file.encoding.pkg",
            "file.encoding",
            "file.separator",
            "path.separator",
            "",
            "netbeans.home",
            "netbeans.user",
            "netbeans.dirs",
            "netbeans.running.environment",
            "netbeans.productversion",
            "netbeans.buildnumber",
            "netbeans.dynamic.classpath",
            "netbeans.logger.console",
            "",
            "java.awt.graphicsenv",
            "java.awt.printerjob",
            "java.class.path",
            "java.class.version",
            "java.endorsed.dirs",
            "java.ext.dirs",
            "java.home",
            "java.io.tmpdir",
            "java.library.path",
            "java.runtime.name",
            "java.runtime.version",
            "java.specification.name",
            "java.specification.vendor",
            "java.specification.version",
            "java.vendor",
            "java.vendor.url.bug",
            "java.vendor.url",
            "java.version",
            "java.vm.info",
            "java.vm.name",
            "java.vm.specification.name",
            "java.vm.specification.vendor",
            "java.vm.specification.version",
            "java.vm.vendor",
            "java.vm.version",
            "",
            "sun.arch.data.model",
            "sun.boot.class.path",
            "sun.boot.library.path",
            "sun.cpu.endian",
            "sun.cpu.isalist",
            "sun.io.unicode.encoding",
            "sun.java.launcher",
            "sun.jnu.encoding",
            "sun.management.compiler",
            "sun.os.patch.level"
        };

        int len = 2;
        for (var key : keys) {
            len = Math.max(len, key.length());
        }

        var envItems = new ArrayList<String>();

        var p = System.getProperties();
        for (var key : keys) {
            if (StringUtils.isBlank(key)) {
                envItems.add("");
            } else if (p.containsKey(key)) {
                envItems.add(StringUtils.rightPad(key, len) + p.getProperty(key, ""));
            }
        }

        return envItems;
    }
}
