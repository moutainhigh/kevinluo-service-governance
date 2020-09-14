package com.kevinluo.storage.framework.utils;

/*
 * Creates on 2020/3/11.
 */

import com.kevinluo.storage.framework.utils.collect.Lists;
import com.kevinluo.storage.framework.utils.collect.Maps;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author lts
 */
public
class StringUtils
{

  // 其他特殊字符的空格
  @SuppressWarnings("CStyleArrayDeclaration")
  public static final String UNICODE_BLANK[] = {
          "\u0000", "\u3000", "\u00a0"};

  /**
   * 字符串是否为空
   *
   * @param input 目标字符串
   * @return true为空，false反之。
   */
  public static boolean isEmpty(String input)
  {
    return input == null || input.length() == 0;
  }

  /**
   * 判断字符串是否不为空
   *
   * @return true表示当前不为空，false反之
   */
  public static boolean isNotEmpty(String input)
  {
    return !isEmpty(input);
  }

  /**
   * 判断是不是null字符，或者是空
   */
  public static boolean isNull(String input)
  {
    return input == null || "null".equals(input);
  }

  /**
   * 判断是否包含并忽略大小写
   */
  public static boolean containsIgnoreCase(String input, String contains)
  {
    if (isEmpty(input) && isEmpty(input))
    {
      return false;
    }
    input = input.toLowerCase();
    contains = contains.toLowerCase();
    return input.contains(contains);
  }

  public static String substring(String input, String indexOf, int fromAppear)
  {
    return substring(input, indexOf, fromAppear, 0, 0);
  }

  public static String substring(String input, String indexOf, int fromAppear, int plus)
  {
    return substring(input, indexOf, fromAppear, plus, 0);
  }

  /**
   * 根据#indexOf出现的位置截取字符串
   *
   * @param input       源字符串
   * @param indexOf     根据indexOf查找索引
   * @param fromAppear  从第几次出现次数开始截取
   * @param plus        从截取到的位置加几个索引
   * @param subtraction 从截取到的位置减几个索引
   * @return 截取后的字符串
   */
  public static String substring(String input, String indexOf, int fromAppear, int plus, int subtraction)
  {
    if (isNotEmpty(input))
    {
      // 判断当前字符是否匹配indexOf的字符
      boolean matches = false;
      // 出现次数计数君
      int fromAppearCount = 0;
      // 当前偏移位置
      int currentOffset = 0;
      char[] inputArray = input.toCharArray();
      char[] indexOfArray = indexOf.toCharArray();
      for (int i = 0; i < inputArray.length; i++)
      {
        if (inputArray[i] == indexOfArray[currentOffset])
        {
          if ((currentOffset + 1) == indexOfArray.length)
          {
            fromAppearCount++;
            if (fromAppearCount == fromAppear)
            {
              return input.substring((i + plus - subtraction));
            } else
            {
              matches = false;
              currentOffset = 0;
              continue;
            }
          }
          matches = true;
          currentOffset++;
        } else
        {
          if (matches)
          {
            matches = false;
            currentOffset = 0;
          }
        }
      }
    }
    return null;
  }

  /**
   * 获取最后一个字符
   *
   * @param input 目标字符串
   * @return 返回该字符串的最后一个字符
   */
  public static String getLast(String input)
  {
    return input.substring(input.length() - 1);
  }

  /**
   * 删除最后一个字符
   *
   * @param input 目标字符串
   * @return 返回处理后的字符串
   */
  public static String delLast(String input)
  {
    return input.substring(0, input.length() - 1);
  }

  /**
   * 获取首字符
   *
   * @param input 目标字符串
   * @return 字符串的首字符
   */
  public static String getFirst(String input)
  {
    return input.substring(0, 1);
  }

  /**
   * 删除第一个字符
   *
   * @param input 目标字符串
   * @return 返回处理后的字符串
   */
  public static String delFirst(String input)
  {
    return input.substring(1, input.length());
  }

  /**
   * 删除首尾字符
   */
  public static String delHeadAndTail(String input)
  {
    return delFirst(delLast(input));
  }

  /**
   * 判断字符串是不是数字
   */
  public static boolean isNumber(String input)
  {
    Pattern pattern = Pattern.compile("[0-9]*");
    return pattern.matcher(input).matches();
  }

  public static String asString(Object input)
  {
    return asString(input, null);
  }

  public static String asString(Object input, String def)
  {
    try
    {
      if (input != null)
      {
        if (input instanceof String)
        {
          return (String) input;
        } else
        {
          return input.toString();
        }
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static int asInt(Object input)
  {
    return asInt(input, 0);
  }

  public static int asInt(Object input, int def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Integer.parseInt(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static Integer asInt0(Object input)
  {
    return asInt0(input, null);
  }

  public static Integer asInt0(Object input, Integer def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Integer.valueOf(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static long asLong(Object input)
  {
    return asLong(input, 0L);
  }

  public static long asLong(Object input, long def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Long.parseLong(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static Long asLong0(Object input)
  {
    return asLong0(input, null);
  }

  public static Long asLong0(Object input, Long def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Long.valueOf(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static float asFloat(Object input)
  {
    return asFloat(input, 0);
  }

  public static float asFloat(Object input, float def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Float.parseFloat(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static Float asFloat0(Object input)
  {
    return asFloat0(input, null);
  }

  public static Float asFloat0(Object input, Float def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Float.valueOf(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static double asDouble(Object input)
  {
    return asDouble(input, 0);
  }

  public static double asDouble(Object input, double def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Double.parseDouble(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static Double asDouble0(Object input)
  {
    return asDouble0(input, null);
  }

  public static Double asDouble0(Object input, Double def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Double.valueOf(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static boolean asBoolean(Object input)
  {
    return asBoolean(input, false);
  }

  public static boolean asBoolean(Object input, boolean def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Boolean.parseBoolean(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static Boolean asBoolean0(Object input)
  {
    return asBoolean0(input, null);
  }

  public static Boolean asBoolean0(Object input, Boolean def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return Boolean.valueOf(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  public static BigDecimal asBigDecimal(Object input)
  {
    return asBigDecimal(input, null);
  }

  public static BigDecimal asBigDecimal(Object input, BigDecimal def)
  {
    try
    {
      if (input != null)
      {
        String strValue = asString(input);
        return new BigDecimal(strValue);
      }
    } catch (Exception ignored)
    {
    }
    return def;
  }

  /**
   * String格式化,大约比String.format()快17倍
   * 格式化的字符为两个花括号"{}"
   */
  public static String format(String input, Object... arguments)
  {
    if (isEmpty(input))
    {
      return input;
    }
    int argsLen = 0;
    int offset = 0;
    int subscript = 0;
    char[] chars = input.toCharArray();
    StringBuilder builder = new StringBuilder();
    char previous = '#';
    for (int i = 0; i < chars.length; i++)
    {
      char current = chars[i];
      if (previous == '{' && current == '}')
      {
        if (argsLen >= arguments.length)
        {
          return builder.toString().concat(new String(chars).substring((i + 1)));
        }
        char[] temp = new char[(i - offset) - 1];
        System.arraycopy(chars, offset, temp, 0, (offset = i - 1));
        builder.append(temp).append(arguments[subscript]);
        temp = new char[chars.length - offset - 2];
        System.arraycopy(chars, offset + 2, temp, 0, temp.length);
        // reset
        chars = temp;
        subscript++;
        i = 0;
        offset = 0;
        argsLen++;
      } else
      {
        previous = current;
      }
    }
    return builder.append(chars).toString();
  }

  /**
   * MessageFormat#format改良版
   *
   * @param input     输入字符串
   * @param arguments 参数
   * @return 格式化后的字符串
   */
  public static String formatMsg(String input, Object... arguments)
  {
    char[] charArray = input.toCharArray();
    // 查找出{0}到{n}的索引位置
    Map<Integer, List<Integer>> indexMap = Maps.newHashMap(); // 存放索引位置的集合
    char index = '#'; // 索引号
    boolean lastIsTrue = false; // 上一个字符是不是左花括号
    for (int i = 0; i < charArray.length; i++)
    {
      char charp = charArray[i];
      if (!lastIsTrue)
      {
        if (charp == '{')
        {
          lastIsTrue = true;
        }
      } else
      {
        if (index == '#')
        {
          if (charp >= 48 && charp <= 57)
          {
            index = charp;
          }
        } else if (charp == '}')
        {
          indexMap.computeIfAbsent((index - '0'), k -> Lists.newArrayList()).add(i);
          ;
        } else
        {
          index = '#';
          lastIsTrue = false;
        }
      }
    }
    // 获取到索引位置后开始格式化
    StringBuilder builder = new StringBuilder(input);
    for (Map.Entry<Integer, List<Integer>> entry : indexMap.entrySet())
    {
      Object value = arguments[entry.getKey()];
      for (Integer i : entry.getValue())
      {
        builder.replace((i - 2), (i + 1), String.valueOf(value));
      }
    }
    return builder.toString();
  }

  /**
   * 将字符串合并成一行
   */
  public static String mergeOneLine(String text)
  {
    StringBuilder content = new StringBuilder();
    StringTokenizer tokenizer = new StringTokenizer(text);
    while (tokenizer.hasMoreTokens())
    {
      String str = tokenizer.nextToken();
      content.append(str);
      if (tokenizer.hasMoreTokens())
      {
        content.append(" ");
      }
    }
    return content.toString();
  }

  /**
   * 替换掉所有为空格的字符
   */
  public static String replaceNull(String input, String... patterns)
  {
    for (String pattern : patterns)
    {
      input = input.replaceAll(pattern, "");
    }
    return input.trim().replaceAll(" ", "").trim();
  }

  /**
   * 对某个字母转换成大写
   *
   * @param index 字符串的下标
   */
  public static String toUpperCase(String input, int index)
  {
    StringBuilder builder = new StringBuilder(input);
    String value = new String(new char[]{input.charAt(index - 1)}).toUpperCase();
    builder.replace(0, 1, value);
    return builder.toString();
  }

  public static String toUpperCase(String input)
  {
    return input.toUpperCase();
  }

  /**
   * 对某个字母转换成小写
   *
   * @param index 字符串的下标
   */
  public static String toLowerCase(String input, int index)
  {
    StringBuilder builder = new StringBuilder(input);
    String value = new String(new char[]{input.charAt(index - 1)}).toLowerCase();
    builder.replace(0, 1, value);
    return builder.toString();
  }

  public static String toLowerCase(String input)
  {
    return input.toLowerCase();
  }

  /**
   * 驼峰转下划线
   */
  public static String humpToUnderline(String string)
  {
    StringBuilder builder = new StringBuilder(string);
    int temp = 0; // 定位
    for (int i = 0, len = string.length(); i < len; i++)
    {
      if (Character.isUpperCase(string.charAt(i)))
      {
        builder.insert(i + temp, "_");
        temp++;
      }
    }
    return builder.toString().toLowerCase();
  }

  /**
   * 下划线转驼峰
   */
  public static String underlineToHump(String string)
  {
    return characterToHump(string, "_");
  }

  /**
   * 根据某个字符分割然后转驼峰命名
   */
  public static String characterToHump(String string, String ch)
  {
    StringBuilder builder = new StringBuilder();
    String[] strs = string.split(ch);
    builder.append(strs[0]);
    for (int i = 1; i < strs.length; i++)
    {
      StringBuilder v = new StringBuilder(strs[i]);
      v.replace(0, 1, String.valueOf(v.charAt(0)).toUpperCase());
      builder.append(v);
    }
    return builder.toString();
  }

  /**
   * 将String数组合并成单个String字符串
   */
  public static String newString(String[] inputs)
  {
    StringBuilder builder = new StringBuilder();
    for (String input : inputs)
    {
      builder.append(input);
    }
    return builder.toString();
  }

  /**
   * 获取异常的堆栈打印
   */
  public static String getStackTrace(Throwable e)
  {
    StringWriter strWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(strWriter);
    try
    {
      e.printStackTrace(printWriter);
      strWriter.close();
      printWriter.close();
    } catch (IOException e1)
    {
      e1.printStackTrace();
    }
    return strWriter.toString();
  }

}

