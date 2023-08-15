import React from 'react'

type props = {
  prompt: string
}

const UserQsn = ({ prompt }: props) => {
  return (
    <div className=''>
      {prompt !== "" &&
        <div className='w-[60%] flex mx-auto  mb-40 mt-10 '>
          <div className='mr-4 font-semibold text-lg mt-8 ' >User</div>
          <div className='bg-gray-200  py-4 px-4 drop-shadow-xl  my-4 rounded-xl whitespace-pre-line w-full'>
            {prompt}
          </div>
        </div>

      }
    </div>
  )
}

export default UserQsn