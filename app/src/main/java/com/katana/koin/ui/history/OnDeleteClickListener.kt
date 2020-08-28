package com.katana.koin.ui.history

import com.katana.koin.data.local.db.entities.Member

interface OnDeleteClickListener  {
    fun onDeleteMember(member: Member)
}