import Loader from "./Loader"

type props = {
  prompt: string,
  loading:boolean
}

const UserQsn = ({ prompt,loading }: props) => {
  return (
    <div className=''>
      {prompt !== "" &&
        <div className='md:w-[60%] mx-2 flex md:mx-auto  mb-2 mt-2 '>
          <div className='mr-4 font-semibold text-xl mt-4 ' >User</div>
          <div className='bg-gray-200 font-medium  py-4 px-4 drop-shadow-xl  rounded-xl whitespace-pre-line w-full'>
            {prompt}
          </div>
        </div>
      }
      {loading && <Loader />}
    </div>
  )
}

export default UserQsn