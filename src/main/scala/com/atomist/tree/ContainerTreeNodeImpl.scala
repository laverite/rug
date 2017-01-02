package com.atomist.tree

/**
  * Simple implementation of ContainerTreeNode allowing fields to be added
  * @param nodeName node name
  * @param nodeType node type
  */
class ContainerTreeNodeImpl(
                             val nodeName: String,
                             override val nodeType: Set[String])
  extends ContainerTreeNode {

  def this(nodeName: String, nt: String) =
    this(nodeName, Set(nt))

  private var kids: Seq[TreeNode] = Seq()

  def addField(tn: TreeNode): Unit = {
    kids = kids :+ tn
  }

  override def childNodeNames: Set[String] = kids.map(_.nodeName).toSet

  override def childNodeTypes: Set[String] = kids.flatMap(_.nodeType).toSet

  override def childrenNamed(key: String): Seq[TreeNode] = kids.filter(_.nodeName.equals(key))

  override def value: String = s"$nodeName:${kids.mkString(",")}"

  override def toString = s"$nodeName:$nodeType(${kids.size})"
}
