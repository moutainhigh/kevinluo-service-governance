package com.kevinluo.storage.framework.utils.net;

/* ************************************************************************
 *
 * Copyright (C) 2020 tiansheng All rights reserved.
 *
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
 *
 * ************************************************************************/

/*
 * Creates on 2020/9/13.
 */

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.net.ftp.parser.TrackUploadProgressBar;

/**
 * 监听{@link org.apache.commons.net.ftp.FTPClient}
 * 上传文件时的进度，用于显示进度条。
 *
 * @author lts
 */
@Getter
@Setter
public class CTrackUploadProgressBar implements TrackUploadProgressBar
{

  /**
   * 文件名
   */
  private String filename;

  /**
   * 流或者文件的总大小
   */
  private long totalBytesTransferred;

  /**
   * 累计传输字节数
   */
  private long grandTotalBytesTransferred;

  /**
   * 当前进度
   */
  private float currentProgressPercent;

  /**
   * 上一个进度是多少
   */
  private int previous;

  public CTrackUploadProgressBar()
  {
    this(0);
  }

  public CTrackUploadProgressBar(long totalBytesTransferred)
  {
    this.totalBytesTransferred = totalBytesTransferred;
  }

  @Override
  public void setTotalBytesTransferred(long totalBytesTransferred)
  {
    this.totalBytesTransferred = totalBytesTransferred;
  }

  @Override
  public void bytesTransferred(long bytesTransferred)
  {
    grandTotalBytesTransferred += bytesTransferred;
    currentProgressPercent = (float) grandTotalBytesTransferred /
            (float) totalBytesTransferred * 100.0F;
    if ((int) currentProgressPercent > previous)
    {
      previous = (int) currentProgressPercent;
    }
  }

  @Override
  public void transferComplete()
  {

  }

}