	.section	__TEXT,__text,regular,pure_instructions
	.globl	_rSum
	.align	4, 0x90
_rSum:                                  ## @rSum
	.cfi_startproc
## BB#0:
	pushq	%rbp
Ltmp2:
	.cfi_def_cfa_offset 16
Ltmp3:
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
Ltmp4:
	.cfi_def_cfa_register %rbp
	subq	$32, %rsp
	movq	%rdi, -16(%rbp)
	movl	%esi, -20(%rbp)
	cmpl	$0, -20(%rbp)
	jg	LBB0_2
## BB#1:
	movl	$0, -4(%rbp)
	jmp	LBB0_3
LBB0_2:
	movq	-16(%rbp), %rax
	movl	(%rax), %ecx
	movq	-16(%rbp), %rax
	addq	$4, %rax
	movl	-20(%rbp), %edx
	subl	$1, %edx
	movq	%rax, %rdi
	movl	%edx, %esi
	movl	%ecx, -24(%rbp)         ## 4-byte Spill
	callq	_rSum
	movl	-24(%rbp), %ecx         ## 4-byte Reload
	addl	%eax, %ecx
	movl	%ecx, -4(%rbp)
LBB0_3:
	movl	-4(%rbp), %eax
	addq	$32, %rsp
	popq	%rbp
	retq
	.cfi_endproc


.subsections_via_symbols
