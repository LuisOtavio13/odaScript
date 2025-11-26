; Modulo LLVM - odaScript
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

@str0 = private unnamed_addr constant [12 x i8] c"hello word\0A\00", align 1
declare i32 @printf(i8*, ...)

define i32 @main() {
entry:
  call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([12 x i8], [12 x i8]* @str0, i64 0, i64 0))
  ret i32 0
}
