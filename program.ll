; Modulo LLVM - odaScript
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

@str0 = private unnamed_addr constant [16 x i8] c"The value is: \0A\00", align 1
@str1 = private unnamed_addr constant [7 x i8] c"Done!\0A\00", align 1
declare i32 @printf(i8*, ...)

define i32 @main() {
entry:
  %0 = alloca i32
  store i32 42, i32* %0
  call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([16 x i8], [16 x i8]* @str0, i64 0, i64 0))
  %t0 = load i32, i32* %0
  call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 %t0)
  call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7 x i8], [7 x i8]* @str1, i64 0, i64 0))
  ret i32 0
}
